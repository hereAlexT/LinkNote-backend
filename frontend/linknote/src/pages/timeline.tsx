import {
  IonContent,
  IonHeader,
  IonPage,
  IonTitle,
  IonToolbar,
  IonList,
  IonItem,
  IonInfiniteScroll,
  IonInfiniteScrollContent,
  IonAvatar,
  IonLabel,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonButton,
  IonPopover
} from '@ionic/react';
import BasicNoteCard from '../components/BasicNoteCard';
import { useState, useEffect } from 'react';
import './timeline.css';


const cardExamples = [
  {
    id: "1",
    createdDate: new Date(),
    body: "body1"
  },
  {
    id: "2",
    createdDate: new Date(),
    body: "body2"
  },
  {
    id: "3",
    createdDate: new Date(),
    body: "body3"
  },
  {
    id: "4",
    createdDate: new Date(),
    body: "body4"
  },
  {
    id: "5",
    createdDate: new Date(),
    body: "body5"
  },
]

const TimeLine: React.FC = () => {
  const [items, setItems] = useState<string[]>([]);

  const generateItems = () => {
    const newItems = [];
    for (let i = 0; i < 50; i++) {
      newItems.push(`Item ${1 + items.length + i}`);
    }
    setItems([...items, ...newItems]);
  };

  useEffect(() => {
    generateItems();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);


  return (
    <IonPage>

      <IonHeader>
        <IonToolbar>
          <IonTitle>Timeline</IonTitle>
        </IonToolbar>

      </IonHeader>
      <IonContent>
        <IonList>
          {items.map((item, index) => (
            <IonItem key={item}>
              <BasicNoteCard id={String(Math.random())} createdDate={new Date()} body="body" />
            </IonItem>
          ))}
        </IonList>
        <IonInfiniteScroll
          onIonInfinite={(ev) => {
            generateItems();
            setTimeout(() => ev.target.complete(), 500);
          }}
        >
          <IonInfiniteScrollContent></IonInfiniteScrollContent>
        </IonInfiniteScroll>
      </IonContent>





    </IonPage>
  );
};

export default TimeLine;
