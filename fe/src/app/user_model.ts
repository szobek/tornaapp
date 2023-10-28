export class User {
    id?: string;
    password?: string;
    firstName?: string;
    lastName?: string;
    token?: string;
    email?:string;
    phone?:string;

    
    constructor(all:any){
        this.firstName=all.first_name
        this.lastName=all.last_name
        this.phone=all.phone
    }
}