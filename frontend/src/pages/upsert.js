import react, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Upsert() {
    const [artistName, setArtistName] = useState('');
    const [albumName, setAlbumName] = useState('');
    const [albumYear, setAlbumYear] = useState(0);

    const upsertData = async () => {
        await axios.put(`/records`,
            {
                artist: artistName,
                albumName: albumName,
                albumYear: albumYear
            });
    }

    return (
        <form>
            <h1>Hello world</h1>
            <div className="mb-3">
                <label placeholder='Artist Name'>Artist Name</label>
                <input placeholder='Artist Name' onChange={(e) => setArtistName(e.target.value)}/>
            </div>
            <div className="mb-3">
                <label placeholder='Album Name' >Album Name</label>
                <input placeholder='Album Name' onChange={(e) => setAlbumName(e.target.value)}/>
            </div>
            <div className="mb-3">
                <label placeholder='Album Year' >Album Year</label>
                <input placeholder='Artist Name' onChange={(e) => setAlbumYear(e.target.value)}/>
            </div>
            <button type="submit" className="btn btn-primary" onClick={upsertData}>Submit</button>
        </form>
    )
}