import {verificationCode} from "@/api/login";

export const useVerificationCode = async (): Promise<IVerificationCode> => {
    const res: { data: IVerificationCode } = await verificationCode()
    return res.data
}