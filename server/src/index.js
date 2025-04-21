const cors = require("cors");
const express = require("express");
const { Pool } = require("pg");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");
require("dotenv").config();

const app = express();
app.use(express.json());

app.use(cors({
    origin: 'http://localhost:3000',  // Your frontend origin
    credentials: true,
    methods: ['GET', 'POST', 'OPTIONS', 'PUT', 'DELETE'],
    allowedHeaders: ['Content-Type', 'Authorization']
}));


// PostgreSQL Connection
const pool = new Pool({
    host: "localhost",
    port: 5432,
    database: "orderbook_db",
    //user: "postgres",
    //password: "postgres",
    user: "sukrutirai",
    password: "",

});

// JWT Configuration
const JWT_SECRET = process.env.JWT_SECRET || "your-secure-secret-key";

// Authentication Middleware
const authenticateToken = (req, res, next) => {
    const authHeader = req.headers['authorization'];
    const token = authHeader && authHeader.split(' ')[1];

    if (!token) return res.sendStatus(401);

    jwt.verify(token, JWT_SECRET, (err, user) => {
        if (err) return res.sendStatus(403);
        req.user = user;
        next();
    });
};

// Routes
app.get('/', (req, res) => {
    res.send('Hello World!');
});

// Signup Endpoint
app.post("/signup", async (req, res) => {
    try {
        const { firstName, lastName, email, password } = req.body;

        // Validation
        if (!firstName || !lastName || !email || !password) {
            return res.status(400).json({ error: "All fields are required" });
        }

        // Check if user exists
        const userExists = await pool.query(
            "SELECT * FROM users WHERE email = $1",
            [email]
        );

        if (userExists.rows.length > 0) {
            return res.status(400).json({ error: "Email already exists" });
        }

        // Hash password and create user
        const hashedPassword = await bcrypt.hash(password, 10);
        const newUser = await pool.query(
            "INSERT INTO users (first_name, last_name, email, password) VALUES ($1, $2, $3, $4) RETURNING id, first_name, last_name, email",
            [firstName, lastName, email, hashedPassword]
        );

        // Generate token
        const token = jwt.sign(
            { userId: newUser.rows[0].id, email: newUser.rows[0].email },
            JWT_SECRET,
            { expiresIn: '36h' }
        );

        res.status(201).json({
            token,
            user: newUser.rows[0]
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: "Registration failed" });
    }
});

// Login Endpoint
app.post("/login", async (req, res) => {
    try {
        const { email, password } = req.body;

        // Check user
        const user = await pool.query(
            "SELECT * FROM users WHERE email = $1",
            [email]
        );

        if (user.rows.length === 0) {
            return res.status(401).json({ error: "Invalid credentials" });
        }

        // Validate password
        const validPassword = await bcrypt.compare(password, user.rows[0].password);
        if (!validPassword) {
            return res.status(401).json({ error: "Invalid credentials" });
        }

        // Generate token
        const token = jwt.sign(
            { userId: user.rows[0].id, email: user.rows[0].email },
            JWT_SECRET,
            { expiresIn: '36h' }
        );

        res.json({
            token,
            user: {
                id: user.rows[0].id,
                first_name: user.rows[0].first_name,
                last_name: user.rows[0].last_name,
                email: user.rows[0].email
            }
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: "Login failed" });
    }
});

app.get("/profile", authenticateToken, async (req, res) => {
    try {
        const user = await pool.query(
            "SELECT id, first_name, last_name, email FROM users WHERE id = $1",
            [req.user.userId]
        );

        if (user.rows.length === 0) {
            return res.status(404).json({ error: "User not found" });
        }

        res.json(user.rows[0]);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: "Failed to fetch profile" });
    }
});

// Protected Routes
app.get("/orderbook", async (req, res) => {
    try {
        const result = await pool.query("SELECT * FROM orderbook");
        res.json(result.rows);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: "Database query failed." });
    }
});

app.get("/marketdata", async (req, res) => {
    try {
        const result = await pool.query("SELECT * FROM marketdata");
        res.json(result.rows);
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: "Database query failed." });
    }
});

// Start Server
const PORT = 3001;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});