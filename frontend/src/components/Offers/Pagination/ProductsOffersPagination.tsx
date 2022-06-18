import React from 'react';
import { Box, Pagination, PaginationItem } from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import { IPage } from '../../../interfaces/pagination/IPagination';
import { setPageHelper } from '../../../helpers/offersPagination';

// eslint-disable-next-line no-unused-vars
type IDoRender = (r: IPage) => void

function ProductsOffersPagination(props: { state: IPage, doRender: IDoRender }) {
  const { state, doRender } = props;
  const { pagesCount } = state;

  const handleChange = (event: React.ChangeEvent<unknown>, page: number) => {
    doRender(setPageHelper(state, page));
  };

  const count = pagesCount > 9 ? 10 : pagesCount;

  return (
    <Box
      display="flex"
      flex="0 1 auto"
      width="100%"
      height="fit-content"
      bgcolor="#B4E197"
      alignItems="center"
      justifyContent="center"
    >
      <Pagination
        count={count}
        page={state.currentPage}
        onChange={handleChange}
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
