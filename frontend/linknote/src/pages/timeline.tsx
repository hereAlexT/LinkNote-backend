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
  IonPopover,
  IonSearchbar
} from '@ionic/react';
import BasicNoteCard from '../components/BasicNoteCard';
import CardEditor from '../components/CardEditor';
import { useState, useEffect } from 'react';
import './timeline.css';
import { Note } from '../shared/interfaces/note.interfaces';

const defaultCards: Note[] = [
  {
    id: "1",
    createdDate: new Date("2023-01-01T00:00:00Z"),
    body: "This is the body of note 1"
  },
  {
    id: "2",
    createdDate: new Date("2023-01-02T00:00:00Z"),
    body: "This is the body of note 2"
  },
  {
    id: "3",
    createdDate: new Date("2023-01-03T00:00:00Z"),
    body: "This is the body of note 3"
  },
  {
    id: "4",
    createdDate: new Date("2023-01-04T00:00:00Z"),
    body: "This is the body of note 4"
  },
  {
    id: "5",
    createdDate: new Date("2023-01-05T00:00:00Z"),
    body: "This is the body of note 5"
  },
];

const TimeLine: React.FC = () => {
  const [cards, setCards] = useState<Note[]>(defaultCards);

  // const generateItems = () => {
  //   const newCards = [];
  //   for (let i = 0; i < 50; i++) {
  //     newCards.push(`Item ${1 + cards.length + i}`);
  //   }
  //   setCards([...cards, ...newCards]);
  // };

  // useEffect(() => {
  //   generateItems();
  //   // eslint-disable-next-line react-hooks/exhaustive-deps
  // }, []);

  const handleOnCreateNote = (noteContent: Note) => {
    setCards([...cards, noteContent])
    console.log(noteContent)
  }


  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Timeline</IonTitle>
        </IonToolbar>
        <IonToolbar>
          <IonSearchbar disabled={true} placeholder="Search function under developing"></IonSearchbar>
        </IonToolbar>
      </IonHeader>
      <IonContent>
        <IonList>
          <IonItem>
            <CardEditor onCreateNote={handleOnCreateNote} />
          </IonItem>

          {cards.map((card, index) => (
            <IonItem key={card.id} button={true} detail={false}>
              <BasicNoteCard id={card.id} createdDate={card.createdDate} body={card.body} />
            </IonItem>
          ))}
        </IonList>
        {/* <IonInfiniteScroll
          onIonInfinite={(ev) => {
            generateItems();
            setTimeout(() => ev.target.complete(), 500);
          }}
        >
          <IonInfiniteScrollContent></IonInfiniteScrollContent>
        </IonInfiniteScroll> */}
      </IonContent>
    </IonPage>
  );
};

export default TimeLine;
