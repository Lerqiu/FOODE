export interface IPage {
    pagesCount: number,
    currentPage: number,
    pageSize: number,
}

export interface IPaginationRaw {
    totalPages: number,
    size: number,
    number: number
}
