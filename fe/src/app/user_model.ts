export class User {
    id?: string;
    password?: string;
    firstName?: string;
    lastName?: string;
    token?: string;
    email?:string;
    phone?:string;

    constructor(forst_name:string,last_name:string){
        this.firstName=forst_name
        this.lastName=last_name
    }
}