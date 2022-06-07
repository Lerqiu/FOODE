const getManagementKey = () => 'offerPageManagement';

export const getOffersPageManagement = () => JSON.parse(localStorage.getItem(getManagementKey()) || '{}');

export const saveOffersPageManagement = (offerPageObject) => {
  localStorage.setItem(getManagementKey(), JSON.stringify(offerPageObject));
};
