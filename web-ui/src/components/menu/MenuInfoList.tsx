import React from 'react';

import global_data from '../../store/global_data';

import MenuInfo from './MenuInfo';

function MenuInfoList() {
  const menuForms = global_data.menuInfos.map((menuInfo: any, i: number) =>
    <MenuInfo index={i} key={`${menuInfo.name}-${menuInfo.content}-${menuInfo.createdBy}-${menuInfo.updatedAt}-${i}`}/>);

  return (
    <div>
      {menuForms}
    </div>
  );
}

export default MenuInfoList;
