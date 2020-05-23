import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';

import global_data from '../../store/global_data';

import MenuInfoName from './MenuInfoName';
import MenuInfoContent from './MenuInfoContent';

interface Props {
  index: number;
}

function MenuInfo(props: Props) {
  const { index } = props;

  const onSaveClick = (event: any) => {
    global_data.saveMenuInfo(index, () => global_data.readMenus(
      () => global_data.updateControlPanel()
    ));
  };

  const onRemoveClick = (event: any) => {
    global_data.removeMenuInfo(index, () => global_data.readMenus(
      () => global_data.updateControlPanel()
    ));
  };

  return (
    <div>
      <MenuInfoName index={index} />
      <MenuInfoContent index={index} />
      <TextField id={'menuForm-created-by-' + index} label="CreatedBy" disabled
        defaultValue={global_data.menuInfos[index].createdBy} margin="dense"/>
      <TextField id={'menuForm-created-at-' + index} label="CreatedAt" disabled
        defaultValue={global_data.formatTime(global_data.menuInfos[index].createdAt)} margin="dense"/>
      <TextField id={'menuForm-updated-by-' + index} label="UpdatedBy" disabled
        defaultValue={global_data.menuInfos[index].updatedBy} margin="dense"/>
      <TextField id={'menuForm-updated-at-' + index} label="UpdatedAt" disabled
        defaultValue={global_data.formatTime(global_data.menuInfos[index].updatedAt)} margin="dense"/>
      <Button variant="contained" color="primary" onClick={onSaveClick}>
        Save
      </Button>
      <Button variant="contained" color="primary" onClick={onRemoveClick}>
        Remove
      </Button>
    </div>
  );
}

export default MenuInfo;
