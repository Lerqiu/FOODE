import { Box, Pagination, PaginationItem } from "@mui/material";
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';


const ProductsOffersPagination = () => {
    return (
      <Box
        display="flex"
        flex="0 1 auto"
        width='100%'
        height="fit-content"
        bgcolor="#B4E197"
        alignItems="center"
        justifyContent="center"
      >
        <Pagination
          count={5}
          renderItem={(item) => (
            <PaginationItem
              components={{ previous: ArrowBackIcon, next: ArrowForwardIcon }}
              {...item}
            />
          )}
        />
      </Box>
    );
  }

export default ProductsOffersPagination;