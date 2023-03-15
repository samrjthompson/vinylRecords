import { useState } from 'react';
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
        <html>
            <head>
                <meta charset="utf-8"/>
                    <meta name="viewport" content="width=device-width, initial-scale=1"/>
                        <title>Bootstrap demo</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous"/>
            </head>
            <body>
                <form>
                    <h1>Hello world</h1>
                    <div className="mb-3">
                        <label className='form-label'>Artist Name</label>
                        <input className='form-control' placeholder='Artist Name' onChange={(e) => setArtistName(e.target.value)} />
                    </div>
                    <div className="mb-3">
                        <label className='form-label'>Album Name</label>
                        <input className='form-control' placeholder='Album Name' onChange={(e) => setAlbumName(e.target.value)} />
                    </div>
                    <div className="mb-3">
                        <label className='form-label'>Album Year</label>
                        <input className='form-control' placeholder='Album Year' onChange={(e) => setAlbumYear(e.target.value)} />
                    </div>
                    <button type="submit" className="btn btn-primary" onClick={upsertData}>Submit</button>
                </form>
                
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
            </body>
        </html>
    )
}