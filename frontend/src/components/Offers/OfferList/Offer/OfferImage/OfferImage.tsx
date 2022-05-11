
const OfferImage = (props: { link: string }) => {
    return (
        <img
            src={props.link}
            style={{ height: '15vh', maxWidth: '100%' }}
        />
    )
}

export default OfferImage;