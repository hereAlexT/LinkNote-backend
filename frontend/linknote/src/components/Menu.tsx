import {
    IonContent,
    IonList,
    IonListHeader,
    IonMenu,
    IonMenuToggle,
    IonItem,
    IonLabel,
    IonIcon
} from "@ionic/react"

import {
    personOutline as personIcon,
    flaskOutline as flaskIcon,
    addOutline as addIcon,
    logInOutline as logInIcon,
    logOutOutline as logOutIcon,
} from 'ionicons/icons';


const routes = {
    appPages: [
        { title: 'Login', path: '/tabs/login', icon: logInIcon },
        { title: 'Signup', path: '/tabs/signup', icon:  addIcon },
        { title: 'Timeline', path: '/tabs/timeline', icon: flaskIcon },
        { title: 'ComponentLab', path: '/tabs/comlab', icon: flaskIcon },
    ],
    loggedInPages: [
        { title: 'Logout', path: '/tabs/login', icon: 'log-out' },
    ],
    loggedOutPages: [
        { title: 'Login', path: '/tabs/login', icon: 'log-in' },
        { title: 'Signup', path: '/tabs/signup', icon: 'person-add' },
    ]
}

interface Pages {
    title: string;
    path: string;
    icon: string;
    routerDirection?: string;
}

interface MenuProps {
}

const Menu: React.FC<MenuProps> = ({ }) => {

    function renderlistItems(list: Pages[]) {
        // Ensure only pages with a path are rendered
        return list
            .filter((route) => !!route.path)
            .map((p) => (
                <IonMenuToggle key={p.title} autoHide={false}>
                    <IonItem routerLink={p.path} routerDirection="none">
                        <IonIcon slot="start" icon={p.icon} />
                        <IonLabel>{p.title}</IonLabel>
                    </IonItem>
                </IonMenuToggle>
            ))

    }
    return (
        <IonMenu contentId="main">
            <IonContent>
                <IonList>
                    <IonListHeader>Menu</IonListHeader>
                    {renderlistItems(routes.appPages)}
                </IonList>
                {/* <IonList>
                    <IonListHeader>Account</IonListHeader>
                    {renderlistItems(routes.loggedInPages)}
                </IonList>
                <IonList>
                    <IonListHeader>Account</IonListHeader>
                    {renderlistItems(routes.loggedOutPages)}
                </IonList> */}
            </IonContent>


        </IonMenu>

    )
}


export default Menu;