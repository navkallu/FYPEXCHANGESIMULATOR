import React, { useState, useEffect } from 'react';
import './marketdata.css';
// import { useAuth } from '../context/AuthContext';

const MarketData = () => {
    const [marketData, setMarketData] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);
    // const { token } = useAuth();


    const fetchMarketData = async () => {
        setIsLoading(true);
        setError(null);

        try {
            const response = await fetch("http://localhost:3001/marketdata");

            if (!response.ok) {
                throw new Error('Failed to fetch market data');
            }

            const data = await response.json();
            setMarketData(data);
        } catch (err) {
            setError(err.message);
            console.error("Error fetching market data:", err);
        } finally {
            setIsLoading(false);
        }
    };

    // Fetch market data from API
    // useEffect(() => {
    //     const fetchMarketData = async () => {
    //         setIsLoading(true);
    //         setError(null);

    //         try {
    //             const response = await fetch("http://localhost:3001/marketdata", {
    //                 headers: {
    //                     'Authorization': `Bearer ${token}`
    //                 }
    //             });

    //             if (!response.ok) {
    //                 throw new Error('Failed to fetch market data');
    //             }

    //             const data = await response.json();
    //             setMarketData(data);
    //         } catch (err) {
    //             setError(err.message);
    //             console.error("Error fetching market data:", err);
    //         } finally {
    //             setIsLoading(false);
    //         }
    //     };

    //     if (token) {
    //         fetchMarketData();
    //     }
    // }, [token]);
    useEffect(() => {
        fetchMarketData();

        // Set up interval to refresh every 2 seconds
        const intervalId = setInterval(fetchMarketData, 2000);

        // Clean up interval on unmount
        return () => clearInterval(intervalId);
    }, []); // Empty dependency array means this runs once on mount

    return (
        <div className="container">
            <div className="selectors">
                <div>
                    <label>Exchange</label>
                    <select className="dropdown" disabled>
                        <option value="HK">HK</option>
                    </select>
                </div>
            </div>

            {isLoading ? (
                <div className="loading">Loading market data...</div>
            ) : error ? (
                <div className="error">Error: {error}</div>
            ) : (
                <div className="order-book">
                    <table>
                        <thead style={{ color: '#1daeff' }}>
                            <tr>
                                <th>Exchange</th>
                                <th>Symbol</th>
                                <th>Best Bid</th>
                                <th>Best Ask</th>
                                <th>High Price</th>
                                <th>Low Price</th>
                                <th>Last Price</th>
                                <th>Executed Qty</th>
                                <th>Total Executed Qty</th>
                                <th>Avg Price</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            {marketData.map((item, index) => (
                                <tr key={index}>
                                    <td>{item.exchange}</td>
                                    <td>{item.symbol}</td>
                                    <td>{item.bidprice}</td>
                                    <td>{item.askprice}</td>
                                    <td>{item.highprice}</td>
                                    <td>{item.lowprice}</td>
                                    <td>{item.lastprice}</td>
                                    <td>{item.executedqty}</td>
                                    <td>{item.totalexecutedqty}</td>
                                    <td>{item.avgprice}</td>
                                    <td>{item.isopen === 'Y' ? 'Open' : 'Closed'}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default MarketData;