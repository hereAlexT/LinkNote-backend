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
    IonIcon
} from '@ionic/react';
import { menuOutline as meanuOutlineIcon } from 'ionicons/icons';
import './BasicNoteCard.css'

interface ContainerProps {
    id: string;
    createdDate: Date;
    body: string;
}

const BasicNoteCard: React.FC<ContainerProps> = ({ id, createdDate, body }) => {
    return (
        <div style={{ width: "100%", margin: 0, padding: 0 }}>
            <IonCard className="">
                <IonCardContent className="ion-no-padding">
                    <IonRow  className="ion-justify-content-end ion-align-items-start" style={{ padding: 0 }}>
                        <IonCol size="auto">
                            <IonButton item-end size="small" fill="clear" id={`note-popover-${id}`}>
                                <IonIcon slot="icon-only" icon={meanuOutlineIcon}></IonIcon>
                            </IonButton>
                            <IonPopover trigger={`note-popover-${id}`} dismissOnSelect={true}>
                                <IonContent>
                                    <IonList>
                                        <IonItem button={true} detail={false}>
                                            Edit
                                        </IonItem>
                                        <IonItem button={true} detail={false}>
                                            Delete
                                        </IonItem>
                                        <IonPopover trigger="nested-trigger" dismissOnSelect={true} side="start">
                                            <IonContent>
                                                <IonList>
                                                    <IonItem button={true} detail={false}>
                                                        Nested option
                                                    </IonItem>
                                                </IonList>
                                            </IonContent>
                                        </IonPopover>
                                    </IonList>
                                </IonContent>
                            </IonPopover>
                        </IonCol>
                    </IonRow>

                    <IonRow> {body}</IonRow>
                    <IonRow><IonCardSubtitle className="">{createdDate.toISOString()}</IonCardSubtitle></IonRow>

                </IonCardContent>
            </IonCard>
        </div>

    );
};

export default BasicNoteCard;
