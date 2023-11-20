import { Redirect, Route } from 'react-router-dom';
import {
  IonApp,
  IonIcon,
  IonLabel,
  IonRouterOutlet,
  IonTabBar,
  IonTabButton,
  IonTabs,
  setupIonicReact
} from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';
import { ellipse, square, triangle } from 'ionicons/icons';
import Signup from './pages/signup';
import Tab2 from './pages/login';
import TimeLine from './pages/timeline';

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

setupIonicReact();

const App: React.FC = () => (
  <IonApp>
    <IonReactRouter>
      <IonTabs>
        <IonRouterOutlet>
          <Route exact path="/signup">
            <Signup />
          </Route>
          <Route exact path="/login">
            <Tab2 />
          </Route>
          <Route path="/timeline">
            <TimeLine />
          </Route>
          <Route exact path="/">
            <Redirect to="/signup" />
          </Route>
        </IonRouterOutlet>
        <IonTabBar slot="bottom">
          <IonTabButton tab="tab1" href="/signup">
            <IonIcon aria-hidden="true" icon={triangle} />
            <IonLabel>Signup</IonLabel>
          </IonTabButton>
          <IonTabButton tab="tab2" href="/login">
            <IonIcon aria-hidden="true" icon={ellipse} />
            <IonLabel>Login</IonLabel>
          </IonTabButton>
          <IonTabButton tab="tab3" href="/timeline">
            <IonIcon aria-hidden="true" icon={square} />
            <IonLabel>Tab 3</IonLabel>
          </IonTabButton>
        </IonTabBar>
      </IonTabs>
    </IonReactRouter>
  </IonApp>
);

export default App;
