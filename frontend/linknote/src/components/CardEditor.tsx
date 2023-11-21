import {
    IonCardContent,
    IonCard,
    IonCardHeader,
    IonCardSubtitle,
    IonCardTitle,
    IonButton,
    IonCol,
    IonRow,
    IonItem, IonList, IonSelect, IonSelectOption, IonPopover, IonContent,
    IonActionSheet,
    IonGrid,
    IonTextarea

} from '@ionic/react';
import { useState, ChangeEvent } from 'react';
import './BasicNoteCard.css';
import { Note } from '../shared/interfaces/note.interfaces';
interface ContainerProps {
    onCreateNote: (noteContent: Note) => void;
}

const CardEditor: React.FC<ContainerProps> = ({ onCreateNote }) => {
    const [content, setContent] = useState('');

    const handleInput = (event: CustomEvent) => {
        const value = event.detail.value;
        setContent(value);
    };
    const submitNote = () => {
        const newNote: Note = {
            id: (Math.random() + 1).toString(36).substring(7),
            createdDate: new Date(),
            body: content
        }
        onCreateNote(newNote);
        setContent(' ');
    }

    return (
        <div className="m-0 p-0 w-full">
            <IonCard>
                <IonCardHeader></IonCardHeader>
                <IonCardContent>
                    <IonGrid>
                        <IonRow>
                            <IonTextarea
                                label="What just heppened?"
                                label-placement="floating"
                                value={content}
                                onIonInput={handleInput}
                                rows={5}></IonTextarea>
                        </IonRow>
                        <IonRow class="ion-justify-content-end">
                            <IonCol size="auto">
                                <IonButton item-end size="small" onClick={submitNote}>Submit</IonButton>
                            </IonCol>
                        </IonRow>
                    </IonGrid>
                </IonCardContent>
            </IonCard>
        </div>
    )
}
export default CardEditor;