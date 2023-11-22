import {
    IonContent, IonHeader, IonInput, IonPage, IonText, IonTitle, IonToolbar, IonButton, IonGrid, IonCol, IonRow,
    IonPopover, IonList, IonItem
} from '@ionic/react';
import { useState } from 'react';


const Login: React.FC = () => {
    const [isTouched, setIsTouched] = useState(false);
    const [isValid, setIsValid] = useState<boolean>();

    const validateEmail = (email: string) => {
        return email.match(
            /^(?=.{1,254}$)(?=.{1,64}@)[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/
        );
    };

    const validate = (ev: Event) => {
        const value = (ev.target as HTMLInputElement).value;

        setIsValid(undefined);

        if (value === '') return;

        validateEmail(value) !== null ? setIsValid(true) : setIsValid(false);
    };

    const markTouched = () => {
        setIsTouched(true);
    };




    return (
        <IonPage>

            <IonHeader>
                <IonToolbar>
                    <IonTitle>Login</IonTitle>
                </IonToolbar>
            </IonHeader>
            <IonContent>
                <IonHeader collapse="condense">
                    <IonToolbar>
                        <IonTitle size="large">Login</IonTitle>
                    </IonToolbar>
                </IonHeader>
                <IonGrid className='ion-padding'>
                    <IonCol>
                        <IonInput
                            className={`${isValid && 'ion-valid'} ${isValid === false && 'ion-invalid'} ${isTouched && 'ion-touched'}`}
                            type="email"
                            fill="solid"
                            label="Email"
                            labelPlacement="floating"
                            helperText="Enter a valid email"
                            errorText="Invalid email"
                            onIonInput={(event) => validate(event)}
                            onIonBlur={() => markTouched()}
                        ></IonInput>
                    </IonCol>
                    <IonCol>
                        <IonInput label="password" labelPlacement="floating" counter={true} maxlength={32} minlength={8}></IonInput>
                    </IonCol>
                    <IonCol>
                        <IonButton expand='block' href="/timeline">Login</IonButton>
                    </IonCol>

                    <IonRow>
                        <IonButton id="popover-button">Open Menu</IonButton>
                        <IonPopover trigger="popover-button" dismissOnSelect={true}>
                            <IonContent>
                                <IonList>
                                    <IonItem button={false}>
                                        Option 1
                                    </IonItem>
                                    <IonItem button={true} detail={false}>
                                        Option 2
                                    </IonItem>
                                </IonList>
                            </IonContent>
                        </IonPopover>
                    </IonRow>

                </IonGrid>






            </IonContent>
        </IonPage>
    );
};

export default Login;