import { Redirect, Route } from 'react-router-dom';
import {
  IonApp,
  IonIcon,
  IonLabel,
  IonRouterOutlet,
  IonTabBar,
  IonTabButton,
  IonTabs,
  setupIonicReact,
  IonMenu,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonSplitPane
} from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';
import { ellipse, square, triangle, cafe } from 'ionicons/icons';


/* Core CSS required for Ionic components to work properly */
import '@ionic/react/css/core.css';

/* Basic CSS for apps built with Ionic */
import '@ionic/react/css/normalize.css';
import '@ionic/react/css/structure.css';
import '@ionic/react/css/typography.css';

/* Optional CSS utils that can be commented out */
import '@ionic/react/css/padding.css';
import '@ionic/react/css/float-elements.css';
import '@ionic/react/css/text-alignment.css';
import '@ionic/react/css/text-transformation.css';
import '@ionic/react/css/flex-utils.css';
import '@ionic/react/css/display.css';


/* Theme variables */
import './theme/variables.css';

import './App.css'

/* Import Components */
import MainTabs from './pages/MainTabs';
import Signup from './pages/Signup';
import Login from './pages/Login';
import TimeLine from './pages/Timeline';
import ComponentLab from './pages/ComponentLab'
import Menu from './components/Menu';


setupIonicReact();

const App: React.FC = () => (

  // <IonApp className='max-w-2xl mx-auto w-full app-background'>
  <IonApp className=''>
    <IonReactRouter>
      <IonSplitPane contentId="main">
        <Menu />
        <IonRouterOutlet id="main">
        <Route path="/" component={Signup} exact={true} />
        <Route path="/tabs" render={() => <MainTabs />} />
        <Route path="/signup" component={Signup} />
        <Route path="/login" component={Login} />
        <Route path="/timeline" component={TimeLine} />
        <Route path="/comlab" component={ComponentLab} />
        </IonRouterOutlet>
      </IonSplitPane>
    </IonReactRouter>
  </IonApp>
);

export default App;
