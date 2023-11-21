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
            <BasicNoteCard noteId="1" body="body" createdDate={new Date()}></BasicNoteCard>
          </IonRow>
          <IonRow>

            <h1 className="text-3xl font-bold underline">
              Hello world!
            </h1>
          </IonRow>
        </IonGrid>
      </IonContent>
    </IonPage>
  )



}

export default ComponentLab;