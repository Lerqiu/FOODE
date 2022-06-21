import { IPage } from '../interfaces/pagination/IPagination';

const pageSizeStr = process.env.REACT_APP_ELEMENTS_PER_PAGE;
const pageSizeNumber = pageSizeStr ? +pageSizeStr : 2;

export const getDefaultPage = (): IPage => ({
  pagesCount: 1,
  currentPage: 1,
  pageSize: pageSizeNumber,
});

export const setPageHelper = (_state: IPage, page: number): IPage => {
  const state = { ..._state };

  if (page <= state.pagesCount && page > 0) { state.currentPage = page; }

  return state;
};
