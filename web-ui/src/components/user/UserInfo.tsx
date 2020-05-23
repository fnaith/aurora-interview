import React from 'react';
import TextField from '@material-ui/core/TextField';

import global_data from '../../store/global_data';

interface Props {
  index: number;
}

function UserInfo(props: Props) {
  const { index } = props;

  return (
    <div>
      <TextField id={'userForm-first-name-' + index} label="firstName" disabled
        defaultValue={global_data.userInfos[index].firstName} margin="dense"/>
      <TextField id={'userForm-last-name-' + index} label="lastName" disabled
        defaultValue={global_data.userInfos[index].lastName} margin="dense"/>
      <TextField id={'userForm-email-' + index} label="email" disabled
        defaultValue={global_data.userInfos[index].email} margin="dense"/>
      <TextField id={'userForm-manager-' + index} label="manager" disabled
        defaultValue={global_data.userInfos[index].manager ? 'True' : 'False'} margin="dense"/>
    </div>
  );
}

export default UserInfo;
