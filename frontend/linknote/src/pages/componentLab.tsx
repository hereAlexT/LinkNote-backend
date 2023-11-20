import {
  IonContent, IonHeader, IonInput, IonPage, IonText, IonTitle, IonToolbar, IonButton, IonGrid, IonCol, IonRow,
  IonPopover, IonList, IonItem
} from '@ionic/react';
import { useState } from 'react';
import EditingCard from '../components/EditingCard'
import BasicNoteCard from '../components/BasicNoteCard';

const ComponentLab: React.FC = () => {
  return (
    <IonPage>
      <IonGrid>
        <IonRow>
          <EditingCard />
        </IonRow>
        <IonRow>
          <BasicNoteCard id="1" body="body" createdDate={new Date()}></BasicNoteCard>
        </IonRow>

      </IonGrid>
    </IonPage>
  )



}

export default ComponentLab;