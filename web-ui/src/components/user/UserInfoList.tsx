import React from 'react';
import Button from '@material-ui/core/Button';

import global_data from '../../store/global_data';

import UserInfo from './UserInfo';

function UserInfoList() {
  const menuForms = global_data.userInfos.map((userInfo: any, i: number) =>
    <UserInfo index={i} key={`${userInfo.email}-${i}`}/>);

  const onSearchClick = (event: any) => {
    global_data.readUsers(() => global_data.readMenus(
      () => global_data.updateControlPanel()
    ));
  };

  return (
    <div>
      {menuForms}
      <Button variant="contained" color="primary" onClick={onSearchClick}>
        Search
      </Button>
    </div>
  );
}

export default UserInfoList;
