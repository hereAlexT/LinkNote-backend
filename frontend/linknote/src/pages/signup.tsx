import { IonContent, IonHeader, IonInput, IonPage, IonText, IonTitle, IonToolbar, IonButton, IonGrid, IonCol, IonRow } from '@ionic/react';
import { useState } from 'react';
import ExploreContainer from '../components/ExploreContainer';
import './signup.css';

const Signup: React.FC = () => {
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
              onIonBlur={() => markTouched()} />
          </IonRow>
          <IonRow>
            <IonInput 
            label="password" 
            helperText="Type your password"
            labelPlacement="floating" 
            counter={true} 
            maxlength={32} 
            minlength={8}></IonInput>
          </IonRow>
          <IonRow>
            <IonInput 
            label="Display Name" 
            helperText="Enter your display name"
            labelPlacement="floating" 
            counter={true} 
            maxlength={32} 
            minlength={8}></IonInput>
          </IonRow>
          <IonRow>
            <IonCol className="ion-padding-top">
              <IonButton expand="block" href="/timeline">Signup</IonButton>
            </IonCol>
          </IonRow>
          <IonRow>
            <IonCol>
              <IonText> - This is a pre-release version . <br/> - Data may not persistent. <br/> - Dont input sensitive data.</IonText>
            </IonCol>
          </IonRow>
        </IonGrid>








      </IonContent>
    </IonPage>
  );
};

export default Signup;
