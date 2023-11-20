import {
  IonContent, IonHeader, IonInput, IonPage, IonText, IonTitle, IonToolbar, IonButton, IonGrid, IonCol, IonRow,
  IonPopover, IonList, IonItem
} from '@ionic/react';
import { useState } from 'react';
import CardEditor from '../components/CardEditor'
import BasicNoteCard from '../components/BasicNoteCard';

const ComponentLab: React.FC = () => {
  return (
    <IonPage>
      <IonContent>
        <IonGrid>
          <IonRow>
            <IonCol>
              <CardEditor />
            </IonCol>

          </IonRow>
          <IonRow>
            <BasicNoteCard id="1" body="body" createdDate={new Date()}></BasicNoteCard>
          </IonRow>
        </IonGrid>
      </IonContent>
    </IonPage>
  )



}

export default ComponentLab;