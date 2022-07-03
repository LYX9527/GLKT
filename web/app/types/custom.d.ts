interface IVerificationCode{
    image: string;
    uuid: string;
}

interface ILoginFrom{
    user: string;
    password: string;
    code: string;
    uuid: string;
}
interface IRegisterFrom{
    user: string;
    password: string;
    code: string;
    uuid: string;
    email: string;
    nickName: string;
}