import {
    IonContent,
    IonList,
    IonListHeader,
    IonMenu,
    IonMenuToggle,
    IonItem,
    IonLabel
} from "@ionic/react"




interface MenuProps {
}

const Menu: React.FC<MenuProps> = ({ }) => {

    return (
        <IonMenu contentId="main-content">
            <IonContent>
                <p>hello</p>
                {/* <IonList lines="none">
                    <IonMenuToggle>
                        <IonItem>
                            <IonLabel>Login</IonLabel>
                        </IonItem>
                    </IonMenuToggle>
                </IonList>

                <IonList lines="none">
                    <IonItem>
                        <IonMenuToggle>
                            <IonLabel>Settings</IonLabel>
                        </IonMenuToggle>
                    </IonItem>
                </IonList> */}
            </IonContent>


        </IonMenu>

    )
}


export default Menu;