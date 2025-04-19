const API_BASE_URL = 'http://localhost:3001';

export const signup = async (userData) => {
    try {
        const response = await fetch(`${API_BASE_URL}/signup`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                firstName: userData.firstName,
                lastName: userData.lastName,
                email: userData.email,
                password: userData.password
            })
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.error || 'Signup failed');
        }

        return await response.json();
    } catch (error) {
        console.error('Signup error:', error);
        throw error;
    }
};

// export const login = async (credentials) => {
//     try {
//         const response = await fetch(`${API_BASE_URL}/login`, {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json',
//             },
//             body: JSON.stringify({
//                 email: credentials.email,
//                 password: credentials.password
//             })
//         });

//         if (!response.ok) {
//             const errorData = await response.json();
//             throw new Error(errorData.error || 'Login failed');
//         }

//         return await response.json();
//     } catch (error) {
//         console.error('Login error:', error);
//         throw error;
//     }
// };

export const login = async (credentials) => {
    try {
        const response = await fetch(`${API_BASE_URL}/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: credentials.email,
                password: credentials.password
            }),
            credentials: 'include' // Important for cookies/sessions if using them
        });

        const data = await response.json();

        if (!response.ok) {
            // Handle specific error messages from backend
            const errorMessage = data.error ||
                (response.status === 401 ? 'Invalid credentials' :
                    response.status === 500 ? 'Server error' : 'Login failed');
            throw new Error(errorMessage);
        }

        return data;
    } catch (error) {
        console.error('Login error:', error);
        throw error;
    }
};