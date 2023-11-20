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
    IonGrid
} from '@ionic/react';
import './BasicNoteCard.css'

interface ContainerProps {

}

const EditingCard: React.FC<ContainerProps> =  ({}) => {
    return (
        <div>
            <IonCard>
                <IonCardHeader></IonCardHeader>
                <IonCardContent>
                    <IonGrid>
                        <IonRow>
                            //todo: the text editor
                        </IonRow>
                        <IonRow>
                            <IonCol>
                                <IonButton>Submit</IonButton>
                            </IonCol>
                        </IonRow>
                    </IonGrid>

                </IonCardContent>
            </IonCard>
        </div>
    )
}
export default EditingCard;