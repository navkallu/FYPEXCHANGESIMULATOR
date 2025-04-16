import React, { useState } from 'react';
import './marketdata.css';

const marketDataJSON = {
    "exchange": "HK",
    "symbols": [
        {
            "symbol": "001.HK",
            "bidprice": 11.0,
            "askprice": 100,
            "highprice": 12.0,
            "lowprice": 9.0,
            "lastprice": 9.0
        },
        {
            "symbol": "002.HK",
            "bidprice": 11.0,
            "askprice": 100,
            "highprice": 12.0,
            "lowprice": 9.0,
            "lastprice": 9.0
        },
        {
            "symbol": "003.HK",
            "bidprice": 11.0,
            "askprice": 100,
            "highprice": 12.0,
            "lowprice": 9.0,
            "lastprice": 9.0
        },
        {
            "symbol": "004.HK",
            "bidprice": 11.0,
            "askprice": 100,
            "highprice": 12.0,
            "lowprice": 9.0,
            "lastprice": 9.0
        }
    ]
};

const MarketData = () => {
    const [selectedExchange, setSelectedExchange] = useState('HK');

    const handleExchangeChange = (e) => {
        setSelectedExchange(e.target.value);
    };

    return (
        <div className="container">
            <div className="selectors">
                <div>
                    <label>Exchange</label>
                    <select
                        className="dropdown"
                        value={selectedExchange}
                        onChange={handleExchangeChange}
                    >
                        <option value="HK">HK</option>
                    </select>
                </div>
            </div>

            <div className="order-book">
                <table>
                    <thead style={{ color: '#1daeff' }}>
                        <tr>
                            <th>Exchange</th>
                            <th>Symbol</th>
                            <th>Best Bid</th>
                            <th>Best Ask</th>
                            <th>Highest Executed Price</th>
                            <th>Lowest Executed Price</th>
                            <th>Last Executed Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        {marketDataJSON.symbols.map((item, index) => (
                            <tr key={index}>
                                <td>{marketDataJSON.exchange}</td>
                                <td>{item.symbol}</td>
                                <td>{item.bidprice}</td>
                                <td>{item.askprice}</td>
                                <td>{item.highprice}</td>
                                <td>{item.lowprice}</td>
                                <td>{item.lastprice}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default MarketData;
