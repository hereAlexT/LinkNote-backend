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
    IonActionSheet
} from '@ionic/react';
import './BasicNoteCard.css'

interface ContainerProps {
    id: string;
    createdDate: Date;
    body: string;
}

const BasicNoteCard: React.FC<ContainerProps> = ({ id, createdDate, body }) => {
    return (
            <IonCard >
                <IonCardHeader>
                </IonCardHeader>
                <IonCardContent>
                    <IonRow>
                        <IonCol>
                            <IonButton id={`open-action-sheet-${id}`}>Open</IonButton>
                            <IonActionSheet
                                trigger={`open-action-sheet-${id}`}
                                header="Actions"
                                buttons={[
                                    {
                                        text: 'Edit',
                                        role: 'destructive',
                                        data: {
                                            action: 'delete',
                                        },
                                    },
                                    {
                                        text: 'Delete',
                                        data: {
                                            action: 'share',
                                        },
                                    },
                                    {
                                        text: 'Cancel',
                                        role: 'cancel',
                                        data: {
                                            action: 'cancel',
                                        },
                                    },
                                ]}
                            ></IonActionSheet>
                        </IonCol>
                    </IonRow>
                    <IonRow>
                        <>
                            <IonButton id={`popover-button-${id}`}>Open Menu</IonButton>
                            <IonPopover trigger={`popover-button-${id}`} dismissOnSelect={true}>
                                <IonContent>
                                    <IonList>
                                        <IonItem button={true} detail={false}>
                                            Option 1
                                        </IonItem>
                                        <IonItem button={true} detail={false}>
                                            Option 2
                                        </IonItem>
                                        <IonItem button={true} id="nested-trigger">
                                            More options...
                                        </IonItem>

                                        <IonPopover trigger="nested-trigger" dismissOnSelect={true} side="end">
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
                        </>
                    </IonRow>

                    <IonRow> {body}</IonRow>
                    <IonRow><IonCardSubtitle className="">{createdDate.toISOString()}</IonCardSubtitle></IonRow>

                </IonCardContent>
            </IonCard>

    );
};

export default BasicNoteCard;
