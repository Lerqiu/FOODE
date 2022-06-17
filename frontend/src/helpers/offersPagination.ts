import { IPage } from '../interfaces/pagination/IPagination';

export const getDefaultPage = (): IPage => ({
  pagesCount: 1,
  currentPage: 1,
  pageSize: 2,
});

export const setPageHelper = (_state: IPage, page: number): IPage => {
  const state = { ..._state };

  if (page <= state.pagesCount && page > 0) { state.currentPage = page; }

  return state;
};
