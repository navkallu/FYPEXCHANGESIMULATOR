import React, { useState, useEffect } from "react";
import { useAuth } from "../context/AuthContext";
import "./Home.css";

const Home = () => {
    const { user } = useAuth();
    const [marketData, setMarketData] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);

    // Fetch market data from API
    const fetchMarketData = async () => {
        setIsLoading(true);
        setError(null);

        try {
            const response = await fetch("http://localhost:3001/marketdata");

            if (!response.ok) {
                throw new Error('Failed to fetch market data');
            }

            const data = await response.json();
            // Sort by executed quantity in descending order and take top 5
            const sortedData = data
                .sort((a, b) => b.executedqty - a.executedqty)
                .slice(0, 5);
            setMarketData(sortedData);
        } catch (err) {
            setError(err.message);
            console.error("Error fetching market data:", err);
        } finally {
            setIsLoading(false);
        }
    };

    useEffect(() => {
        // Fetch immediately on mount
        fetchMarketData();

        // Set up interval to refresh every 2 seconds
        const intervalId = setInterval(fetchMarketData, 2000);

        // Clean up interval on unmount
        return () => clearInterval(intervalId);
    }, []);

    return (
        <div className="home-container">
            <div className="welcome-section">
                <h1>Welcome, {user ? `${user.first_name}${user.last_name ? ` ${user.last_name}` : ''}` : 'Guest'}!</h1>
            </div>

            <div className="active-stocks-section">
                <h2>Most Active Stocks</h2>

                {isLoading ? (
                    <div className="loading">Loading market data...</div>
                ) : error ? (
                    <div className="error">Error: {error}</div>
                ) : (
                    <div className="table-container">
                        <table className="active-stocks-table">
                            <thead>
                                <tr>
                                    <th className="tablehead">Exchange</th>
                                    <th className="tablehead">Stock Symbol</th>
                                    <th className="tablehead">Executed Quantity</th>
                                    <th className="tablehead">Last Price</th>
                                    <th className="tablehead">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                {marketData.map((item, index) => (
                                    <tr key={index}>
                                        <td className="tablehead">{item.exchange}</td>
                                        <td className="tablehead">{item.symbol}</td>
                                        <td className="tablehead">{item.executedqty}</td>
                                        <td className="tablehead">{item.lastprice}</td>
                                        <td className="tablehead">
                                            <span className={`status ${item.isopen === 'Y' ? 'open' : 'closed'}`}>
                                                {item.isopen === 'Y' ? 'Open' : 'Closed'}
                                            </span>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                )}
            </div>
        </div>
    );
};

export default Home;