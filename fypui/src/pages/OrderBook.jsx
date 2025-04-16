import React, { useState } from "react";
import "./orderbook.css";
import MarketData from "./MarketData";
import Home from "./Home";

const data = {
    exchange: "HK",
    symbols: [
        {
            symbol: "001.HK",
            bid_orders: [
                {
                    price: 11,
                    openQuantity: 100,
                    orderId: "1743773110703",
                    entryTime: 1743773110701,
                    closed: false,
                    filled: false,
                },
                {
                    price: 10,
                    openQuantity: 100,
                    orderId: "1743773103201",
                    entryTime: 1743773103216,
                    closed: false,
                    filled: false,
                },
            ],
            ask_orders: [
                {
                    price: 11,
                    openQuantity: 100,
                    orderId: "1743773110703",
                    entryTime: 1743773110701,
                    closed: false,
                    filled: false,
                },
                {
                    price: 10,
                    openQuantity: 100,
                    orderId: "1743773103201",
                    entryTime: 1743773103216,
                    closed: false,
                    filled: false,
                },
            ],
        },
        {
            symbol: "002.HK",
            bid_orders: [
                {
                    price: 11,
                    openQuantity: 100,
                    orderId: "1743773110703",
                    entryTime: 1743773110701,
                    closed: false,
                    filled: false,
                },
                {
                    price: 10,
                    openQuantity: 100,
                    orderId: "1743773103201",
                    entryTime: 1743773103216,
                    closed: false,
                    filled: false,
                },
            ],
            ask_orders: [
                {
                    price: 11,
                    openQuantity: 100,
                    orderId: "1743773110703",
                    entryTime: 1743773110701,
                    closed: false,
                    filled: false,
                },
                {
                    price: 10,
                    openQuantity: 100,
                    orderId: "1743773103201",
                    entryTime: 1743773103216,
                    closed: false,
                    filled: false,
                },
            ],
        },
        {
            symbol: "003HK",
            bid_orders: [
                {
                    price: 11,
                    openQuantity: 100,
                    orderId: "1743773110703",
                    entryTime: 1743773110701,
                    closed: false,
                    filled: false,
                },
                {
                    price: 10,
                    openQuantity: 100,
                    orderId: "1743773103201",
                    entryTime: 1743773103216,
                    closed: false,
                    filled: false,
                },
            ],
            ask_orders: [
                {
                    price: 11,
                    openQuantity: 100,
                    orderId: "1743773110703",
                    entryTime: 1743773110701,
                    closed: false,
                    filled: false,
                },
                {
                    price: 10,
                    openQuantity: 100,
                    orderId: "1743773103201",
                    entryTime: 1743773103216,
                    closed: false,
                    filled: false,
                },
            ],
        },
    ],
};

export default function OrderBook() {
    const [selectedSymbol, setSelectedSymbol] = useState("");
    const [selectedTab, setSelectedTab] = useState("home");

    const selectedData = data.symbols.find((s) => s.symbol === selectedSymbol);

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
                    <div className="selectors">
                        <div>
                            <label>Exchange</label>
                            <select className="dropdown" disabled>
                                <option value="{data.exchange}">{data.exchange}</option>
                            </select>
                        </div>
                        <div>
                            <label>Stock Symbol</label>
                            <select
                                className="dropdown"
                                onChange={(e) => setSelectedSymbol(e.target.value)}
                            >
                                <option value="">Select</option>
                                {data.symbols.map((s) => (
                                    <option key={s.symbol} value={s.symbol}>
                                        {s.symbol}
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
                                    {selectedData?.bid_orders.map((order) => (
                                        <tr key={order.orderId}>
                                            <td>{order.orderId}</td>
                                            <td>{order.price}</td>
                                            <td>{order.openQuantity}</td>
                                            <td>{order.entryTime}</td>
                                        </tr>
                                    )) || <tr><td colSpan="4">No data</td></tr>}
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
                                    {selectedData?.ask_orders.map((order) => (
                                        <tr key={order.orderId}>
                                            <td>{order.orderId}</td>
                                            <td>{order.price}</td>
                                            <td>{order.openQuantity}</td>
                                            <td>{order.entryTime}</td>
                                        </tr>
                                    )) || <tr><td colSpan="4">No data</td></tr>}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </>) : (
                <MarketData />
            )}

        </div>
    );
}
