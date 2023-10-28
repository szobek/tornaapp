import { UserData } from "./user-data";

export class User {
    id?: string;
    password?: string;
    firstName?: string;
    lastName?: string;
    token?: string;
    email?:string;
    phone?:string;

    
    constructor(all:UserData){
        this.firstName=all.firstName
        this.lastName=all.lastName
        this.phone=all.phone
    }
}