import react, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Home() {
    const [records, setRecords] = useState([]);
    useEffect(() => {
        getRecords();
    }, []);

    const getRecords = async () => {
        const response = await axios.get('http://localhost:8181/records');
        setRecords(response.data);
    };

    return (
        <table className="table">
            <thead>
                <tr>
                    <th scope="col">Number</th>
                    <th scope="col">Artist</th>
                    <th scope="col">Album Year</th>
                </tr>
            </thead>
            <tbody>
                {
                    records.map((record, index) => (
                        <tr>
                            <th scope="row" key={index}>{index+1}</th>
                            <td>{record.artist}</td>
                            <td>{record.albumYear}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
    )
}