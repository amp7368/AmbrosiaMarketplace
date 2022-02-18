import { persistState } from '@datorama/akita';
import { render } from 'react-dom';
import 'reflect-metadata';
import App from './app/App';
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';

const rootEl = document.getElementById('root');
persistState();
render(<App />, rootEl);
