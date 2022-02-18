export default function endpointAuth(ambrosia: any) {
    ambrosia.get(function (req: any, res: any) {
        res.send('Hi Hi');
    });
}
