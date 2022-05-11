import IUser from "../user/IUser";

interface IFridge {
    id: number,
    fridgeItems: any,
    user: IUser
}
export default IFridge;