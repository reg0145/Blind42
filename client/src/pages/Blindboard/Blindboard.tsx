import { useState, useEffect, useRef } from 'react';
import { useQuery } from 'react-query';
import { Link, useNavigate } from 'react-router-dom';
import Header from 'components/Header/Header';
import Footer from 'components/Footer/Footer';
import PostPreview from 'components/Postpreview/Postpreview';
import PageNation from 'components/PageNation/PageNation';
import instance from 'utils/functions/axios';
import { AppContainer, PageContainer, TopBar, PageName, Squares, ContentFooterWrap, Category } from 'styles/styled';
import { PostContainer, PostWrap, ContentWrap } from './styled';
import { BoardData, ContentData} from 'utils/functions/type';
import Loading from 'pages/Loading/Loading';
import Error from 'pages/Error/Error';

function Blindboard() {
	const [boardData, setBoardData] = useState({contents: [], page: 0, pages: 0});
	const {page, pages} = boardData
	const currentUrl = window.location.href;
	const urlId = currentUrl.split('blindboard?page=')[1];
	const navigate = useNavigate();

	const { isLoading, error, data } = useQuery(['blindboard_key'], 
																			() => {instance.get(`/board?boardId=1&page=${urlId}`).then((res) => {setBoardData(res.data);})},
																			{retry: 0});

	// useEffect(() => {
	// 	instance
	// 	.get(`/board?boardId=1&page=${urlId}`)
	// 	.then((res) => {setBoardData(res.data)})
	// 	.catch((err) => console.log(err));
	// }, [window.location.href])

	const pageChangeHandler = (page: number) => {
		navigate(`/blindboard?page=${page}`)
  };

	if (isLoading) return <Loading />

	if (error) return <Error />

	return (
		<>
			<AppContainer>
				<Header />
				<PageContainer>
					<TopBar>
						<PageName>ꉂꉂ ( ˆᴗˆ  ) 블라인드 게시판</PageName>
						<Squares>
							<div>&#9866;</div>
							<div>&#10064;</div>
							<Link to='/'>
								<div>&times;</div>
							</Link>
						</Squares>
					</TopBar>
					<ContentFooterWrap>
						<PostContainer>
							<Category>
								<div></div>
								<div>제목</div>
								<div>조회</div>
								<div>추천</div>
								<div>작성일</div>
							</Category>
							<ContentWrap>
								<PostWrap>
									{boardData.contents.map((el: ContentData, idx) => {
										return (<PostPreview key={idx} postData={el} />)
									})}
								</PostWrap>
							</ContentWrap>
							<PageNation
								curPage={page}
								totalPages={pages}
								pageChangeHandler={pageChangeHandler}
							/>
						</PostContainer>
						<Footer />
					</ContentFooterWrap>
				</PageContainer>
			</AppContainer>
		</>
	);
}

export default Blindboard;