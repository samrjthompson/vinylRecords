import logo from './logo.svg';
import './App.css';
import { Component } from 'react';

class App extends Component {
  state = {
    clients: []
  };

  async componentDidMount() {
    const response = await fetch('/vinylrecords/1234');
    const body = await response.json();
    this.setState({ records: body });
  }

  render() {
    const { records } = this.state;
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro">
            <h2>Clients</h2>
            {records.map(record =>
              <div key={record.id}>
              </div>
            )}
          </div>
        </header>
      </div>
    );
  }
}

export default App;

