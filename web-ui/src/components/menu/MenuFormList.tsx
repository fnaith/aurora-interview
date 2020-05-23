import React from 'react';

import global_data from '../../store/global_data';

import MenuForm from './MenuForm';

function MenuFormList() {
  const menuForms = global_data.menuForms.map((menuForm, i) => <MenuForm index={i} key={`${menuForm.name}-${menuForm.content}-${i}`}/>);

  return (
    <div>
      {menuForms}
    </div>
  );
}

export default MenuFormList;
