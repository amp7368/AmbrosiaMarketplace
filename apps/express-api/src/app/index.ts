import express from 'express';
import endpointAuth from './auth/expressAuth';

// create new express app and save it as "app"
const app = express();

// server configuration
const PORT = 80;

const ambrosia = app.route('/ambrosia/auth');
const root = app.route('/');

endpointAuth(root);
app.listen(PORT, () => {
    console.log(`Server running at: http://localhost:${PORT}/`);
});
export {};
