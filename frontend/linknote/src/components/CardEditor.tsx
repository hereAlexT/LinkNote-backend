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
                            <IonTextarea
                                label="Notes here"
                                labelPlacement="stacked"
                                placeholder="What just happened?"
                            />

                        </IonRow>
                        <IonRow>
                     
                            <IonCol size="12" className="ion-text-right">
                                <IonButton item-end>Submit</IonButton>
                            </IonCol>
                        </IonRow>
                    </IonGrid>
                </IonCardContent>
            </IonCard>
        </div>
    )
}
export default CardEditor;