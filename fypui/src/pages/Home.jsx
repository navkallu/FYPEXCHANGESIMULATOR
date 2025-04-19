import React from "react";
import { useAuth } from "../context/AuthContext";
import "./Home.css";

const Home = () => {
    const { user } = useAuth();
    
    // Your existing dummy data
    const mostActiveStocks = [
        {
            exchange: "HK",
            symbol: "001.HK",
            totalQuantity: 125000,
            avgPrice: 45.67,
            isOpen: true,
            sender: "User123"
        },
        {
            exchange: "HK",
            symbol: "002.HK",
            totalQuantity: 98700,
            avgPrice: 32.15,
            isOpen: false,
            sender: "User456"
        },
        {
            exchange: "HK",
            symbol: "003.HK",
            totalQuantity: 75400,
            avgPrice: 28.90,
            isOpen: true,
            sender: "User789"
        },
        {
            exchange: "HK",
            symbol: "004.HK",
            totalQuantity: 63200,
            avgPrice: 15.25,
            isOpen: true,
            sender: "User101"
        },
        {
            exchange: "HK",
            symbol: "005.HK",
            totalQuantity: 52100,
            avgPrice: 42.30,
            isOpen: false,
            sender: "User202"
        }
    ];

    return (
        <div className="home-container">
            <div className="welcome-section">
                <h1>Welcome, {user ? `${user.first_name}${user.last_name ? ` ${user.last_name}` : ''}` : 'Guest'}!</h1>
            </div>

            <div className="active-stocks-section">
                <h2>Most Active Stocks</h2>

                <div className="table-container">
                    <table className="active-stocks-table">
                        <thead>
                            <tr>
                                <th className="tablehead">Exchange</th>
                                <th className="tablehead">Stock Symbol</th>
                                <th className="tablehead">Total Executed Quantity</th>
                                <th className="tablehead">Average Price</th>
                                <th className="tablehead">Is Open</th>
                                <th className="tablehead">Order Sender</th>
                            </tr>
                        </thead>
                        <tbody>
                            {mostActiveStocks.map((stock, index) => (
                                <tr key={index}>
                                    <td className="tablehead">{stock.exchange}</td>
                                    <td className="tablehead">{stock.symbol}</td>
                                    <td className="tablehead">{stock.totalQuantity.toLocaleString()}</td>
                                    <td className="tablehead">{stock.avgPrice.toFixed(2)}</td>
                                    <td className="tablehead">
                                        <span className={`status ${stock.isOpen ? 'open' : 'closed'}`}>
                                            {stock.isOpen ? 'Open' : 'Closed'}
                                        </span>
                                    </td>
                                    <td className="tablehead">{stock.sender}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
                <a href="http://" className="orderbutton" target="_blank" rel="noopener noreferrer">Order Sender</a>
            </div>
        </div>
    );
};

export default Home;