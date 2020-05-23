import React from 'react';
import Button from '@material-ui/core/Button';

import global_data from '../../store/global_data';

import MenuFormName from './MenuFormName';
import MenuFormContent from './MenuFormContent';

interface Props {
  index: number;
}

function MenuForm(props: Props) {
  const { index } = props;

  const onClick = (event: any) => {
    global_data.removeMenuForm(index);
    global_data.updateControlPanel();
  };

  return (
    <div>
      <MenuFormName index={index} />
      <MenuFormContent index={index} />
      <Button variant="contained" color="primary" onClick={onClick}>
        Remove
      </Button>
    </div>
  );
}

export default MenuForm;
