import React, { useState, useEffect } from "react";
import "./orderbook.css";
import MarketData from "./MarketData";
import Home from "./Home";
// import { useAuth } from "../context/AuthContext";

const OrderBook = () => {
    const [selectedSymbol, setSelectedSymbol] = useState("");
    const [selectedTab, setSelectedTab] = useState("home");
    const [orderBookData, setOrderBookData] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
    // const { token } = useAuth();


    const fetchOrderBookData = async () => {
        setIsLoading(true);
        setError(null);

        try {
            const response = await fetch("http://localhost:3001/orderbook");

            if (!response.ok) {
                throw new Error('Failed to fetch order book data');
            }

            const data = await response.json();
            setOrderBookData(data);
        } catch (err) {
            setError(err.message);
            console.error("Error fetching order book:", err);
        } finally {
            setIsLoading(false);
        }
    };
    // Fetch order book data from API
    useEffect(() => {
        // const fetchOrderBookData = async () => {
        //     setIsLoading(true);
        //     setError(null);

        //     try {
        //         const response = await fetch("http://localhost:3001/orderbook", {
        //             headers: {
        //                 'Authorization': `Bearer ${token}`
        //             }
        //         });

        //         if (!response.ok) {
        //             throw new Error('Failed to fetch order book data');
        //         }

        //         const data = await response.json();
        //         setOrderBookData(data);
        //     } catch (err) {
        //         setError(err.message);
        //         console.error("Error fetching order book:", err);
        //     } finally {
        //         setIsLoading(false);
        //     }
        // };


        // if (selectedTab === "orderbook") {
        //     fetchOrderBookData();
        // }

        // Fetch immediately on mount
        fetchOrderBookData();

        // Set up interval to refresh every 2 seconds
        const intervalId = setInterval(fetchOrderBookData, 2000);

        // Clean up interval on unmount
        return () => clearInterval(intervalId);
    }, []);

    // Parse the stringified order arrays
    const parseOrders = (ordersString) => {
        try {
            return JSON.parse(ordersString);
        } catch (e) {
            console.error("Error parsing orders:", e);
            return [];
        }
    };

    // Get symbols for dropdown
    const symbols = orderBookData.map(item => item.symbol);

    // Get selected symbol data
    const selectedData = orderBookData.find(item => item.symbol === selectedSymbol);

    // Format timestamp to readable time
    const formatTime = (timestamp) => {
        return new Date(timestamp).toLocaleTimeString();
    };

    return (
        <div className="container">
            <div className="header">
                <div className="title">Stock Exchange Simulator</div>
                <div className="nav-buttons">
                    <button
                        className={`nav-btn ${selectedTab === "home" ? "active" : ""}`}
                        onClick={() => setSelectedTab("home")}
                    >
                        Home
                    </button>
                    <button
                        className={`nav-btn ${selectedTab === "orderbook" ? "active" : ""}`}
                        onClick={() => setSelectedTab("orderbook")}
                    >
                        Order Book
                    </button>
                    <button
                        className={`nav-btn ${selectedTab === "marketdata" ? "active" : ""}`}
                        onClick={() => setSelectedTab("marketdata")}
                    >
                        Market Data
                    </button>
                </div>
            </div>

            {selectedTab === "home" ? (
                <Home />
            ) : selectedTab === "orderbook" ? (
                <>
                    {isLoading && <div className="loading">Loading order book data...</div>}
                    {error && <div className="error">Error: {error}</div>}

                    <div className="selectors">
                        <div>
                            <label>Exchange</label>
                            <select className="dropdown" disabled>
                                <option value="HK">HK</option>
                            </select>
                        </div>
                        <div>
                            <label>Stock Symbol</label>
                            <select
                                className="dropdown"
                                value={selectedSymbol}
                                onChange={(e) => setSelectedSymbol(e.target.value)}
                                disabled={isLoading}
                            >
                                <option value="">Select</option>
                                {symbols.map((symbol) => (
                                    <option key={symbol} value={symbol}>
                                        {symbol}
                                    </option>
                                ))}
                            </select>
                        </div>
                    </div>

                    <div className="order-book">
                        <div className="orderbook-container">
                            <div className="order-header bid">Bid</div>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Order ID</th>
                                        <th>Bid Price</th>
                                        <th>Quantity</th>
                                        <th>Time</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {selectedData ? (
                                        parseOrders(selectedData.bid_orders).map((order) => (
                                            <tr key={order.orderId}>
                                                <td>{order.orderId}</td>
                                                <td>{order.price.toFixed(2)}</td>
                                                <td>{order.openQuantity}</td>
                                                <td>{formatTime(order.entryTime)}</td>
                                            </tr>
                                        ))
                                    ) : (
                                        <tr>
                                            <td colSpan="4">
                                                {selectedSymbol ? "No orders found" : "Please select a symbol"}
                                            </td>
                                        </tr>
                                    )}
                                </tbody>
                            </table>
                        </div>
                        <div className="orderbook-container">
                            <div className="order-header ask">Ask</div>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Order ID</th>
                                        <th>Ask Price</th>
                                        <th>Quantity</th>
                                        <th>Time</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {selectedData ? (
                                        parseOrders(selectedData.ask_orders).map((order) => (
                                            <tr key={order.orderId}>
                                                <td>{order.orderId}</td>
                                                <td>{order.price.toFixed(2)}</td>
                                                <td>{order.openQuantity}</td>
                                                <td>{formatTime(order.entryTime)}</td>
                                            </tr>
                                        ))
                                    ) : (
                                        <tr>
                                            <td colSpan="4">
                                                {selectedSymbol ? "No orders found" : "Please select a symbol"}
                                            </td>
                                        </tr>
                                    )}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </>
            ) : (
                <MarketData />
            )}
        </div>
    );
};

export default OrderBook;