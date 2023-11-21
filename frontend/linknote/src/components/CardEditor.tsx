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
import './BasicNoteCard.css'

interface ContainerProps {

}

const CardEditor: React.FC<ContainerProps> = ({ }) => {
    return (
        <div>
            <IonCard>
                <IonCardHeader></IonCardHeader>
                <IonCardContent>
                    <IonGrid>
                        <IonRow>
                            <IonTextarea label="What just heppened?" label-placement="floating" rows={5}></IonTextarea>
                        </IonRow>
                        <IonRow class="ion-justify-content-end">
                            <IonCol size="auto">
                                <IonButton item-end size="small">Submit</IonButton>
                            </IonCol>
                        </IonRow>
                    </IonGrid>
                </IonCardContent>
            </IonCard>
        </div>
    )
}
export default CardEditor;