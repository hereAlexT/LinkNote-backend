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
import { Note, NoteId} from '../shared/interfaces/note.interfaces';

const defaultCards: Note[] = [
  // {
  //   noteId: "1",
  //   createdDate: new Date("2023-01-01T00:00:00Z"),
  //   body: "This is the body of note 1"
  // },
  // {
  //   noteId: "2",
  //   createdDate: new Date("2023-01-02T00:00:00Z"),
  //   body: "This is the body of note 2"
  // },
  // {
  //   noteId: "3",
  //   createdDate: new Date("2023-01-03T00:00:00Z"),
  //   body: "This is the body of note 3"
  // },
  // {
  //   noteId: "4",
  //   createdDate: new Date("2023-01-04T00:00:00Z"),
  //   body: "This is the body of note 4"
  // },
  // {
  //   noteId: "5",
  //   createdDate: new Date("2023-01-05T00:00:00Z"),
  //   body: "This is the body of note 5"
  // },
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
    setCards([...cards, noteContent]);
  }

  const handleOnDeleteNote = (noteId: NoteId) => {
    const updateCards = cards.filter(card => card.noteId !== noteId);
    setCards(updateCards);
  }

  const handleOnEditNote = (noteId: NoteId) => {
    console.log("Request to edit:" + noteId)
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
            <IonItem key={card.noteId} button={true} detail={false}>
              <BasicNoteCard 
              noteId={card.noteId} 
              createdDate={card.createdDate} 
              body={card.body} 
              onDeleteNote={handleOnDeleteNote}
              onEditNote={handleOnEditNote}
              />
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
