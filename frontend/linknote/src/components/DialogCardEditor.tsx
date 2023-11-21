import {
    IonCardContent,
    IonCard,
    IonCardHeader,
    IonCardSubtitle,
    IonCardTitle,
    IonButton,
    IonCol,
    IonRow,
    IonItem,
    IonList,
    IonSelect,
    IonSelectOption,
    IonPopover,
    IonContent,
    IonActionSheet,
    IonIcon,
    IonPage,
    IonHeader,
    IonToolbar,
    IonModal,
    IonLabel
} from '@ionic/react';
import { menuOutline as meanuOutlineIcon } from 'ionicons/icons';
import { Note, NoteId } from '../shared/interfaces/note.interfaces';
import { personCircle } from 'ionicons/icons';
import { useRef } from 'react';

interface ContainerProps {
    note: Note;
    onDissmiss: () => void
}

const DialogCardEditor: React.FC<ContainerProps> = ({ note, onDissmiss }) => {

    const modal = useRef<HTMLIonModalElement>(null);

    function dismiss() {
        modal.current?.dismiss();
    }


    return (
        <IonModal id="example-modal" ref={modal} trigger="open-custom-dialog">
            <div className="wrapper">
                <h1>Dialog header</h1>
                <IonList lines="none">
                    <IonItem button={true} detail={false} onClick={dismiss}>
                        <IonIcon icon={personCircle}></IonIcon>
                        <IonLabel>Item 1</IonLabel>
                    </IonItem>
                    <IonItem button={true} detail={false} onClick={dismiss}>
                        <IonIcon icon={personCircle}></IonIcon>
                        <IonLabel>Item 2</IonLabel>
                    </IonItem>
                    <IonItem button={true} detail={false} onClick={dismiss}>
                        <IonIcon icon={personCircle}></IonIcon>
                        <IonLabel>Item 3</IonLabel>
                    </IonItem>
                </IonList>
            </div>
        </IonModal>

    )
}

export default DialogCardEditor;