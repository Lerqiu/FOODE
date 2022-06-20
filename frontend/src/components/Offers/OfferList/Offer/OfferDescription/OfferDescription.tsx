import React from 'react';

function OfferDescription(props: { description: string }) {
  const { description } = props;
  return (
    <div style={
            {
              color: 'white',
              backgroundColor: '#82bc75',
              borderRadius: '10px',
              padding: '5px',
              fontSize: '0.7rem',
              height: '100%',
            }
        }
    >
      {description}
    </div>
  );
}

export default OfferDescription;
