import IFridge from "../fridge/IFridge";

interface IUser {
    id: number
    login: string,
    points: number,
    contact: string,
    fridge: IFridge
}
export default IUser;