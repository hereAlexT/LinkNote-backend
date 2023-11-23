import { IonContent, IonHeader, IonInput, IonPage, IonText, IonTitle, IonToolbar, IonButton, IonGrid, IonCol, IonRow, IonButtons, IonMenuButton } from '@ionic/react';
import { useState } from 'react';


const Signup: React.FC = () => {

    /* Email Validation */
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
    /* Email Validation */

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [displayName, setDisplayName] = useState("");

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        console.log(email, password, displayName)
    }





    return (
        <IonPage id="main">
            <IonHeader>
                <IonToolbar>
                    <IonButtons slot="start">
                        <IonMenuButton />
                    </IonButtons>
                    <IonTitle>Sign up</IonTitle>
                </IonToolbar>
            </IonHeader>
            <IonContent>
                <IonHeader collapse="condense">
                    <IonToolbar>
                        <IonTitle size="large">Signup</IonTitle>
                    </IonToolbar>
                </IonHeader>
                <IonGrid className='ion-padding'>
                    <form onSubmit={handleSubmit}>
                        <IonRow>
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
                                onIonChange={(e) => setEmail(e.detail.value!)}
                                disabled={true}
                            />

                        </IonRow>
                        <IonRow>
                            <IonInput
                                type="password"
                                label="Password"
                                helperText="Type your password"
                                labelPlacement="floating"
                                counter={true}
                                maxlength={32}
                                minlength={8}
                                onIonChange={(e) => setPassword(e.detail.value!)}
                                disabled={true}
                            />
                        </IonRow>
                        <IonRow>
                            <IonInput
                                label="Display Name"
                                helperText="Enter your display name"
                                labelPlacement="floating"
                                counter={true}
                                maxlength={32}
                                minlength={8}
                                onIonChange={(e) => setDisplayName(e.detail.value!)}
                                disabled={true}
                            />
                        </IonRow>
                        <IonRow>
                            <IonCol className="ion-padding-top">
                                <IonButton expand="block" href="/timeline" disabled>Signup not supported in pre-release</IonButton>
                            </IonCol>
                        </IonRow>
                        <IonRow>
                            <IonCol>
                                <IonText> - This is a pre-release version . <br /> - Data may not persistent. <br /> - Dont input sensitive data.</IonText>
                            </IonCol>
                        </IonRow>
                    </form>
                </IonGrid>








            </IonContent>
        </IonPage>
    );
};

export default Signup;